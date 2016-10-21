angular.module("app", []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}).controller("home", function ($http, $location) {
    var self = this;
    $http.get("/user").success(function (data) {
        self.auth = data.userAuthentication;
        self.user = data.userAuthentication.details.name;
        self.authenticated = true;
        console.log(data.userAuthentication)
        $http.get("/user/findByFacebookId/"+self.auth.details.id).success(function (data) {

                var options = '';
                for(var i = 0; i < data.things.length; i++){
                    var id =data.things[i].id;
                    options+='<p><input name="thing" type="radio" value="'+id+'">'+id+'</p>';
                }
                document.getElementById('selectThing').innerHTML = options;
                $('#table').bootstrapTable({
                    data: data.things,
                    formatLoadingMessage: function () {
                                return '';
                    }
                });
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