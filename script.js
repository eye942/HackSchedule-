info = [];
info[0] = true;
info[1] = true;
info[2] = true;


$.ajax({
   type: "POST",
   data: {info:info},
   url: "http://localhost:8080/results",
   success: function(msg){
     $('.answers').html(msg);
   }
});
