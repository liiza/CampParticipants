var app = angular.module("app", []);

app.controller('form', function($scope, httpService){
	function setOrganizations(response){
		$scope.organizations = response.data.records;
	}
	$scope.saveParticipant = function(){
		$scope.participant.Organization = [$scope.participant.Organization.id];
		httpService.saveParticipant({"fields": $scope.participant}, function(response){console.log(response)});
	}
	httpService.fetchOrganizations(setOrganizations);
});

app.controller('home', function($scope, httpService) {

	function setParticipants(participants){
		$scope.participants = participants;
	}
	function filterParticipantsByOrganization(organizationId) {
		function belongsToOrganization(participant){
			return participant.fields.Organization.indexOf(organizationId) !== -1;
		}
		return function(response) {
			setParticipants(response.data.records.filter(belongsToOrganization));
		}
	}
	httpService.fetchOrganizations(setOrganizations);

	$scope.$watch("selectedOrganization",
		function() {
			if ($scope.selectedOrganization) {
				httpService.fetchParticipants(filterParticipantsByOrganization($scope.selectedOrganization.id));
			}
		},
		true);

});


app.factory("httpService", function($http){
	return {
		fetchOrganizations: function(callback){
			$http.get("/organizations")
				.then(callback);

		},
		fetchParticipants: function(callback) {
			$http.get("/participants")
				.then(callback);

		},
		saveParticipant : function(participant, callback) {
			$http.post("/participant", participant)
				.then(callback);
		}

	}
});