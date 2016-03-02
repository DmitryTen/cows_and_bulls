(function($, undefined){
  $(function(){
     $("div.header .main-menu").on("click", ".playgame", function(){
      $("div.header .main-menu ul li a").removeClass("current");
      $(this).addClass("current");
    });

    $("div.header .main-menu").on("click", ".rules", function(){
      $("div.header .main-menu ul li a").removeClass("current");
      $(this).addClass("current");
//      $("div.playgame, div.statistics").hide();
//      $("div.rules").show();
    });

    $("div.header .main-menu").on("click", ".statistics", function(){
      $("div.header .main-menu ul li a").removeClass("current");
      $(this).addClass("current");
//      $("div.playgame, div.rules").hide();
    });


  });


})(jQuery);