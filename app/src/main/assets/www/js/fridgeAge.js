var dataArray = [];
var yearIntervals = ["0-4", "4-8", "8-12", "12-16", "16-20", "20-24",
                    "24-28", "28-32", "32-36", "36-40"];

// Array of random numbers while we sync with backend
var min = 0, max = 10, upTo = 10, i;

for (i = min; i < upTo; i++) {
    dataArray.push(Math.random()*500);
}

var margin = {top: 20, bottom: 120, left: 60, right: 10},
    width = 545,
    height = 500,
    chartWidth = width - margin.left - margin.right,
    chartHeight = height - margin.top - margin.bottom;

var xPos = d3.scale.ordinal()
    .domain(yearIntervals)
    .rangeRoundBands([0, chartWidth], .3);

var yPos = d3.scale.linear()
    .domain([0, 500])
    .range([chartHeight, 0]);

var xAxis = d3.svg.axis()
    .scale(xPos)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(yPos)
    .orient("left")
    .ticks(10);

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
    .text("Number of Refrigerators");

canvas.append("text")
    .attr("class", "xAxisLabel")
    .attr("x", 180)
    .attr("y", 405)
    .text("Age (Years)")

canvas.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0, " + chartHeight + ")")
    .call(xAxis)

canvas.append("g")
    .attr("class", "y axis")
    .call(yAxis)

var bars = canvas.selectAll(".bar")
    .data(dataArray)
    .enter().append("rect")
        .attr("class", "bar")
        .attr("x", function (d, i) { return xPos(yearIntervals[i]); })
        .attr("width", xPos.rangeBand())
        .attr("y", function (d) {return yPos(d); })
        .attr("height", function(d) { return chartHeight - yPos(d); })
        .attr("fill", "blue");