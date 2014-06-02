Android.showToast("Howdy!");

function randHeat() {
    return "heat-" + ( 6 - Math.round(Math.pow(Math.random(), 3) * 6));
}

var width = 640,
    height = 552;

var zoom = d3.behavior.zoom()
    .scaleExtent([1, 10])
    .on("zoom", zoomed);

var drag = d3.behavior.drag()
    .origin(function(d) { return d; })
    .on("dragstart", dragstarted)
    .on("drag", dragged)
    .on("dragend", dragended);

var projection = d3.geo.albers()
    .center([0, 30])
    .rotate([-70, 0])
    .parallels([23, 37])
    .scale(4 * height)
    .translate([width * 0.5, height * 0.53]);

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

d3.json("pakistan.json", function(error, pak) {
    var provinces = topojson.feature(pak, pak.objects.provinces),
    districts = topojson.feature(pak, pak.objects.subdistricts);

    container.selectAll(".district")
            .data(districts.features)
        .enter().append("path")
            .attr("class", function(d) { return "district " + d.properties.name3.replace(/[ .]/g, "") + " " + randHeat(); })
            .attr("d", path);

    container.append("path")
        .datum(topojson.mesh(pak, pak.objects.subdistricts, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "district-boundary");

    container.append("path")
        .datum(topojson.mesh(pak, pak.objects.provinces, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "province-boundary");

    svg.selectAll(".district").on("click", function() {
        svg.selectAll(".selected")
            .classed("selected", false)
            .style("fill", "");

        d3.select(this)
            .classed("selected", true)
            .style("fill", "#4cc");
    });
});

function zoomed() {
  container.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
}

function dragstarted(d) {
  d3.event.sourceEvent.stopPropagation();
  d3.select(this).classed("dragging", true);
}

function dragged(d) {
  d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
}

function dragended(d) {
  d3.select(this).classed("dragging", false);
}