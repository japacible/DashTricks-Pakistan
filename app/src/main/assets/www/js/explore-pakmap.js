function randHeat() {
  return "heat-" + ( 5 - Math.round(Math.pow(Math.random(), 3) * 5));
}

var legendText = [
  "NO DATA",
  "0-20%",
  "20-40%",
  "40-60%",
  "60-80%",
  "80-100%"
];

var heatData = window.WebAppInterface.getDistrictHeatNumberAsJson();
console.log(heatData);

var width = 640,
  height = 552;

var zoom = d3.behavior.zoom()
  .scaleExtent([1, 10])
  .on("zoom", zoomed);

var projection = d3.geo.albers()
  .center([0, 30])
  .rotate([-70, 0])
  .parallels([23, 37])
  .scale(4 * height)
  .translate([width * 0.5, height * 0.5]);

var path = d3.geo.path()
  .projection(projection)
  .pointRadius(2);

var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height)
  .append("g")
    .call(zoom);

var rect = svg.append("rect")
  .attr("width", width)
  .attr("height", height)
  .style("fill", "none")
  .style("pointer-events", "all");

var container = svg.append("g");

d3.select("body").selectAll(".legend")
    .data([0,1,2,3,4,5])
  .enter().append("div")
    .style("left", function(d) { return width/7 * d + "px"; })
    .style("width", function(d) { return width/7 + "px"; })
    .style("top", height-110 + "px")
    .attr("class", function(d) { return "legend heat-" + d; })
    .text(function(d) { return legendText[d]; });

d3.select("body").append("button")
  .attr("type", "button")
  .attr("class", "reset btn btn-default btn-lg")
  .on("click", function() {
    zoom.translate([0,0]).scale(1);
    container.attr("transform", "translate(" + zoom.translate() + ")scale(" + zoom.scale() + ")");
  })
  .append("span")
    .attr("class", "glyphicon glyphicon-refresh");

d3.json("pakistan.json", function(error, pak) {
  var provinces = topojson.feature(pak, pak.objects.provinces),
  districts = topojson.feature(pak, pak.objects.districts);

  container.selectAll(".district")
      .data(districts.features)
    .enter().append("path")
      .attr("class", function(d) {
        var dname = d.properties.district;
        var heat;
        if (dname in heatData) {
          heat = "heat-" + Math.min(Math.floor(heatData[dname]/20 + 1), 5);
        } else {
          heat = "heat-0";
        }

        return "district " + d.properties.district.replace(/[ .]/g, "") + " " + heat; 
      })
      .attr("d", path);

  container.append("path")
    .datum(topojson.mesh(pak, pak.objects.districts, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
    .attr("d", path)
    .attr("class", "district-boundary");

  container.append("path")
    .datum(topojson.mesh(pak, pak.objects.provinces, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
    .attr("d", path)
    .attr("class", "province-boundary");

  svg.selectAll(".district").on("click", function(d) {
    if (d3.event != null && d3.event.defaultPrevented) return;
    svg.selectAll(".selected")
      .classed("selected", false);

    d3.select(this)
      .classed("selected", true);

    Fragment.callFragment(d.properties.district);
  });
});

function zoomed() {
  container.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
}