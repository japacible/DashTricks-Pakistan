var width = 640,
height = 552;

// d3.select("#display").style("max-height", height + "px");

var drag = d3.behavior.drag()
    .origin(function(d) { return d; })
    .on("drag", dragmove);

var zoom = d3.behavior.zoom()
    .translate([0,0])
    .scale(1)
    .scaleExtent([1, 8])
    .on("zoom", zoomed);

function randSD() {
    return "rgb(" + Math.pow(Math.round(Math.random()*6), 3) + ",110,210)";
}

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
    .attr("height", height);

var features = svg.append("g");

svg.append("rect")
    .attr("class", "overlay")
    .attr("width", width)
    .attr("height", height)
    .call(zoom);

d3.json("pakistan.json", function(error, pak) {
    var provinces = topojson.feature(pak, pak.objects.provinces),
    // districts = topojson.feature(pak, pak.objects.districts),
    districts = topojson.feature(pak, pak.objects.subdistricts);

    features.selectAll(".bdistrict")
            .data(districts.features)
        .enter().append("path")
            .attr("class", function(d) { return "district " + d.properties.name3.replace(/[ .]/g, ""); })
            .attr("d", path)
            .style("fill", function(d) { return randSD(); });

    features.append("path")
        .datum(topojson.mesh(pak, pak.objects.subdistricts, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "district-boundary");

    features.append("path")
        .datum(topojson.mesh(pak, pak.objects.provinces, function(a, b) { return a !== b; })) // return a !== b; for tangential borders only
        .attr("d", path)
        .attr("class", "province-boundary");
/*
    svg.selectAll(".province-label")
            .data(provinces.features)
        .enter().append("text")
            .attr("class", function(d) { return "province-label " + d.properties.name1.replace(/[ .]/g, ""); })
            .attr("transform", function(d) { return "translate(" + path.centroid(d) + ")"; })
            .attr("dy", ".35em")
            .text(function(d) { return d.properties.name; });
*/
    svg.selectAll(".district").on("click", function() {
        // d3.selectAll("#display tbody tr").remove()

        svg.selectAll(".selected")
            .classed("selected", false)
            .style("fill", randSD());

        d3.select(this)
            .classed("selected", true)
            .style("fill", "#4cc");
/*
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
*/
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

function dragmove(d) {

}

function zoomed() {
    features.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
    //features.select(".state-border").style("stroke-width", 1.5 / d3.event.scale + "px");
    //features.select(".county-border").style("stroke-width", .5 / d3.event.scale + "px");
}