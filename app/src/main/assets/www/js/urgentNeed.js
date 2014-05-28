var dataArray = [1523, 605];
var need = ["Okay", "Needs Attention"];

// Array of random numbers while we sync with backend
var min = 0, max = 100, upTo = 36, i;

var margin = {top: 20, bottom: 80, left: 70, right: 10},
    width = 480,
    height = 500,
    chartWidth = width - margin.left - margin.right,
    chartHeight = height - margin.top - margin.bottom;

var xPos = d3.scale.ordinal()
    .domain(need)
    .rangeRoundBands([0, chartWidth], .5);

var yPos = d3.scale.linear()
    .domain([0, d3.max(dataArray) + 300])
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
    .attr("y", -54)
    .attr("transform", "rotate(-90)")
    .text("Number of Facilities");

canvas.append("text")
    .attr("class", "xAxisLabel")
    .attr("x", 180)
    .attr("y", 430)
    .text("State")

canvas.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0, " + chartHeight + ")")
    .call(xAxis)
    .selectAll("text")
        .attr("y", 10);

canvas.append("g")
    .attr("class", "y axis")
    .call(yAxis)

var bars = canvas.selectAll(".bar")
    .data(dataArray)
    .enter().append("rect")
        .attr("class", function(d, i) { return "bar" + i; } )
        .attr("x", function (d, i) { return xPos(need[i]); })
        .attr("width", xPos.rangeBand())
        .attr("y", function (d) {return yPos(d); })
        .attr("height", function(d) { return chartHeight - yPos(d); })
        .attr("fill", "blue");

// Add in OK number on top of OK bar
canvas.append("text")
    .attr("class", "xAxisLabels")
    .attr("y", yPos(dataArray[0]) - 2)
    .attr("x", xPos(need[0]) + 25)
    .text(dataArray[0]);

canvas.append("text")
    .attr("class", "xAxisLabels")
    .attr("y", yPos(dataArray[1]) - 2)
    .attr("x", xPos(need[1]) + 30)
    .text(dataArray[1]);