function randHeat() {
	return "heat-" + ( 5 - Math.round(Math.pow(Math.random(), 3) * 5));
}

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

d3.selectAll("g").on("dblclick.zoom", null);

var container = svg.append("g");

var reset = container.append();

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
	//console.log("zoomed");
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