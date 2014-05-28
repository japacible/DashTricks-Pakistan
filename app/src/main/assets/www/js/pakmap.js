var width = window.innerWidth,
height = window.innerHeight;

d3.select("#display").style("max-height", height + "px");

function randSD() {
    return "rgb(" + Math.pow(Math.round(Math.random()*6), 3) + ",110,210)";
}

var selected = {};

var projection = d3.geo.albers()
    .center([0, 30])
    .rotate([-70, 0])
    .parallels([23, 37])
    .scale(3.5*height)
    .translate([width * 0.3, height * 0.53]);

var path = d3.geo.path()
    .projection(projection)
    .pointRadius(2);

var svg = d3.select("body").append("svg")
    .attr("width", width * 0.55)
    .attr("height", height);

var facName = svg.append("text")
    .attr("class", "display")
    .attr("x", "800px")
    .attr("y", "200px");

var facCov = svg.append("text")
    .attr("class", "display")
    .attr("x", "800px")
    .attr("y", "250px");

d3.json("pakistan.json", function(error, pak) {
    var provinces = topojson.feature(pak, pak.objects.provinces),
    districts = topojson.feature(pak, pak.objects.districts),
    subdistricts = topojson.feature(pak, pak.objects.subdistricts);
/*
    var sds = [];
    var regions = {};
    for (var i = 0; i < subdistricts.features.length; i++) {
        var name1 = subdistricts.features[i].properties.name1;
        var name2 = subdistricts.features[i].properties.name2;
        var name3 = subdistricts.features[i].properties.name3;
        if (regions[name1] == null) regions[name1] = {};
        if (regions[name1][name2] == null) regions[name1][name2] = {};
        if (regions[name1][name2][name3] == null) regions[name1][name2][name3] = {};
        sds[i] = subdistricts.features[i].properties.name3;
    }
    console.log(sds.sort());
    console.log(JSON.stringify(regions));
*/
    svg.selectAll(".subdistrict")
            .data(subdistricts.features)
        .enter().append("path")
            .attr("class", function(d) { return "subdistrict " + d.properties.name3.replace(/[ .]/g, ""); })
            .attr("d", path)
            .style("fill", function(d) { return randSD(); });

    svg.append("path")
        .datum(topojson.mesh(pak, pak.objects.subdistricts, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "subdistrict-boundary");

    svg.append("path")
        .datum(topojson.mesh(pak, pak.objects.districts, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "district-boundary");

    svg.append("path")
        .datum(topojson.mesh(pak, pak.objects.provinces, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "province-boundary");

    svg.selectAll(".province-label")
            .data(provinces.features)
        .enter().append("text")
            .attr("class", function(d) { return "province-label " + d.properties.name1.replace(/[ .]/g, ""); })
            .attr("transform", function(d) { return "translate(" + path.centroid(d) + ")"; })
            .attr("dy", ".35em")
            .text(function(d) { return d.properties.name; });

    svg.selectAll(".subdistrict").on("click", function() {
        d3.selectAll("#display tbody tr").remove()

        svg.selectAll(".selected")
            .classed("selected", false)
            .transition()
                .style("fill", randSD());

        d3.select(this)
            .classed("selected", true)
            .transition()
                .style("fill", "#4cc");

        var dataset = [];
        for (var i = 0; i < Math.random()*12; i++) {
            dataset[i] = "Fake Fac " + (i+1);
        }
        var tr = d3.select("#display tbody").selectAll("tr")
                .data(dataset);

        var enter = tr.enter().append("tr");

        enter.append("td")
            .text(function(d) { return d; });

        enter.append("td")
            .text(function(d) { return Math.round(Math.random()*70+20) + "%"; });
    });
});
/*
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
*/