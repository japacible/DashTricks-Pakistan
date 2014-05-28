var dataArray = [];
var districtArray = ["Attock", "Bahawalnagar", "Bahawalpur", "Bhakkar", "Chakwal", "Chiniot",
    "Dera Ghazi Khan", "Faisalabad", "Gujranwala", "Gujrat", "Hafizabad", "Jhang", "Jhelum",
    "Kasur", "Khanewal", "Khushab", "Lahore", "Layyah", "Lodhran", "Mandi Bahauddin", "Mianwali",
    "Multan", "Muzaffargarh", "Narowal", "Nankana Sahib", "Okara", "Pakpattan", "Rahim Yar Khan",
    "Rajanpur", "Rawalpindi", "Sahiwal", "Sargodha", "Sheikhupura", "Sialkot", "Toba Tek Singh",
    "Vehari"];

// Truncate values to fit into the view
var truncatedDistricts = districtArray.map(function(d) {
        var nameLength = d.length;
        if(nameLength > 9) {
            var trunc = d.substring(0, 8);
            return trunc;
        }
        return d;
    });

// Array of random numbers while we sync with backend
var min = 0, max = 100, upTo = 36, i;

for (i = min; i < upTo; i++) {
    dataArray.push(Math.random());
}

var margin = {top: 20, bottom: 120, left: 60, right: 10},
    width = 510,
    height = 500,
    chartWidth = width - margin.left - margin.right,
    chartHeight = height - margin.top - margin.bottom;

var xPos = d3.scale.ordinal()
    .domain(truncatedDistricts)
    .rangeRoundBands([0, chartWidth], .1);

var yPos = d3.scale.linear()
    .domain([0, 1])
    .range([chartHeight, 0]);

var xAxis = d3.svg.axis()
    .scale(xPos)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(yPos)
    .orient("left")
    .ticks(20, "%");

var canvas = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height)
    .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// Append graph items to svg canvas
canvas.append("text")
    .attr("class", "yLabel")
    .attr("x", -240)
    .attr("y", -48)
    .attr("transform", "rotate(-90)")
    .text("Refrigerator Deficiency");

canvas.append("text")
    .attr("class", "xAxisLabel")
    .attr("x", 180)
    .attr("y", 438)
    .text("District")

canvas.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0, " + chartHeight + ")")
    .call(xAxis)
    .selectAll("text")
        .style("text-anchor", "end")
        .attr("dx", "-.8em")
        .attr("dy", ".15em")
        .attr("transform", function(d) {
            return "rotate(-65)"
            });

canvas.append("g")
    .attr("class", "y axis")
    .call(yAxis)

var bars = canvas.selectAll(".bar")
        .data(dataArray)
    .enter().append("rect")
        .attr("class", "bar")
        .attr("x", function (d, i) { return xPos(truncatedDistricts[i]); })
        .attr("width", xPos.rangeBand())
        .attr("y", function (d) {return yPos(d); })
        .attr("height", function(d) { return chartHeight - yPos(d); })
        .attr("fill", "blue");