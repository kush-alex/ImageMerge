angular.module("app", []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}).controller("home", function ($scope, $http, $location) {
    var self = this;
    $scope.loading = true;
    $http.get("/user").success(function (data) {
        self.auth = data.userAuthentication;
        self.user = data.userAuthentication.details.name;
        self.authenticated = true;
        console.log(data.userAuthentication)
        $http.get("/user/findByFacebookId/"+self.auth.details.id).success(function (data) {

                var options = '';
                for(var i = 0; i < data.things.length; i++){
                    var id =data.things[i].id;
                    var field =data.things[i].field;
                    options+='<option value="'+id+'">'+field+'</p>';
                    $('#recordTable').append("<tr><td>"+ id +"</td><td>"+ field +"</td><td><form id='form"+id+"' method='get' action='/image' target='_blank'  class='form-inline'><input type='hidden' value='"+id+"' name='item'/><button value='Get image' class='btn btn-primary'>Get image</button></form></td><tr>")
                }
                document.getElementById('chooseRecord').innerHTML = options;
        })
    }).error(function () {
        self.user = "N/A";
        self.authenticated = false;
    });
    self.logout = function () {
        $http.post('logout', {}).success(function () {
            self.authenticated = false;
            $location.path("/");
        }).error(function (data) {
            console.log("Logout failed")
            self.authenticated = false;
        });
    };
});