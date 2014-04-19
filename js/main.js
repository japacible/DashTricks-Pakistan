$('.nav-tabs li a').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
  $('.tab-content > .tab-pane.active').jScrollPane();
});
