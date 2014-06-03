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

var width = 640,
  height = 552;

var zoom = d3.behavior.zoom()
  .scaleExtent([1, 10])
  .on("zoom", zoomed);
/*
var drag = d3.behavior.drag()
  .origin(function(d) { return d; })
  .on("dragstart", dragstarted)
  .on("drag", dragged)
  .on("dragend", dragended);
*/
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

//d3.selectAll("g").on("dblclick.zoom", null);

var container = svg.append("g");

d3.select("body").selectAll(".legend")
    .data([0,1,2,3,4,5])
  .enter().append("div")
    .style("left", function(d) { return width/6 * d + "px"; })
    .style("width", function(d) { return width/6 + "px"; })
    .style("top", height-30 + "px")
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
      .attr("class", function(d) { return "district " + d.properties.district.replace(/[ .]/g, "") + " " + randHeat(); })
      .attr("d", path);

  container.append("path")
    .datum(topojson.mesh(pak, pak.objects.districts, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
    .attr("d", path)
    .attr("class", "district-boundary");

  container.append("path")
    .datum(topojson.mesh(pak, pak.objects.provinces, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
    .attr("d", path)
    .attr("class", "province-boundary");

  svg.selectAll(".district").on("click", function() {
    if (d3.event != null && d3.event.defaultPrevented) return;
    svg.selectAll(".selected")
      .classed("selected", false);

    d3.select(this)
      .classed("selected", true);
  });
});

function zoomed() {
  container.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
}
/*
function dragstarted(d) {
  d3.event.sourceEvent.stopPropagation();
  d3.select(this).classed("dragging", true);
  console.log("dragstarted");
}

function dragged(d) {
  d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
  console.log("dragged");
}

function dragended(d) {
  d3.select(this).classed("dragging", false);
  console.log("dragended");
}
*/