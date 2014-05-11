var width = 1280,
height = 800;

var projection = d3.geo.albers()
	.center([0, 30])
	.rotate([-70, 0])
	.parallels([23, 37])
	.scale(3000)
	.translate([450, 450]);

var path = d3.geo.path()
	.projection(projection)
	.pointRadius(2);

var svg = d3.select("body").append("svg")
	.attr("width", width)
	.attr("height", height);

var facName = svg.append("text")
	.attr("class", "display")
	.attr("x", "800px")
	.attr("y", "200px");

var facCov = svg.append("text")
	.attr("class", "display")
	.attr("x", "800px")
	.attr("y", "250px");

d3.json("pakistan2.json", function(error, pak) {
	var provinces = topojson.feature(pak, pak.objects.provinces),
		places = topojson.feature(pak, pak.objects.places);

	svg.selectAll(".province")
			.data(provinces.features)
		.enter().append("path")
			.attr("class", function(d) { return "province " + d.properties.name.replace(/[ .]/g, ""); })
			.attr("d", path);

	svg.append("path")
		.datum(topojson.mesh(pak, pak.objects.provinces, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
		.attr("d", path)
		.attr("class", "province-boundary");

	svg.selectAll(".province-label")
			.data(provinces.features)
		.enter().append("text")
			.attr("class", function(d) { return "province-label " + d.properties.name.replace(/[ .]/g, ""); })
			.attr("transform", function(d) { return "translate(" + path.centroid(d) + ")"; })
			.attr("dy", ".35em")
			.text(function(d) { return d.properties.name; });
});

d3.json("fake_data.json", function(error, fake) {
	var facilities = fake.facilities;

	svg.selectAll(".facility")
			.data(facilities)
		.enter().append("circle")
			.attr("class", function(d) { return "facility " + d.facility_name.replace(/[ .]/g, ""); })
			.attr("cx", function(d) { return projection([d.longitude, d.latitude])[0]; })
			.attr("cy", function(d) { return projection([d.longitude, d.latitude])[1]; })
			.attr("r", 20);

	svg.selectAll(".facility").on("click", function(fac) {
		svg.selectAll(".facility").transition()
			.attr("r", 20)
			.style("fill-opacity", 0);
		d3.select(this).transition()
			.attr("r", 40)
			.style("fill-opacity", .4);

		facName.text(fac.properties.name);
		facCov.text("Coverage: " + fac.properties.coverage + "%");
	});
});