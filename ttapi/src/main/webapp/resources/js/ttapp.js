var ttApp = angular.module('ttApp', ['textAngular']);

ttApp.directive('ckEditor', [function () {
    return {
        require: '?ngModel',
        link: function ($scope, elm, attr, ngModel) {
        	   var config = {
                       toolbar:[[ 'Bold', 'Italic', 'Underline', 'Strike', 'TextColor', 'FontSize', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight' ]],
                       width: '100%',
                       height: '30.00vh',
                       autoParagraph: false
                   };
            var ck = CKEDITOR.replace(elm[0], config);

            ck.on('change', function () {
                $scope.$apply(function () {
                    ngModel.$setViewValue(ck.getData());
                });
            });

            ngModel.$render = function (value) {
                ck.setData(ngModel.$modelValue);
            };
        }
    };
}])

ttApp.controller('ApiController', ['$scope', '$http', function($scope, $http) {
	var processData = function(data) {
		// Sort
		if($scope.options.sort && data) {
			data = _.sortBy(data, $scope.options.sort);
			if($scope.options.sortDirection && $scope.options.sortDirection === 'descending') {
				$scope.options.reverse();
			}
		}		
		return data;
	};
		
	$scope.init = function(url, options) {
		if(!url) {
			return;
		}
		$scope.dataUrl = url;
		
		$scope.options = options || {};
		$scope.options.start = $scope.options.start || 0;
		$scope.options.amount = $scope.options.amount || 50;
		
		$http.get(url)
			.success(function(data, status, headers, config) {
				$scope.data = processData(data);
				$scope.nextStart = 25;
				if($scope.options.callback && typeof $scope.options.callback === 'function') {
					$scope.options.callback.apply(null, [$scope.data]);
				}
		    })
		    .error(function(data, status, headers, config) {
		    	$scope.data = {error: true};
		    });
	};
	
	$scope.loadMore = function(increment) {
		console.log('loading');
		if(increment){
			$scope.increment = increment
		}else{
			$scope.increment = 25;
		}
		$scope.nextEnd = $scope.nextStart + $scope.increment;
		url = 'api/ft/start/' +$scope.nextStart + '/end/' + $scope.nextEnd;
		$scope.nextStart = $scope.nextEnd + 1;
		$scope.nextEnd = $scope.nextStart + $scope.increment;
		$http.get(url)
			.success(function(data, status, headers, config) {
				$scope.data = $scope.data.concat(processData(data));
				$scope.options.start += $scope.options.amount;
			})
		    .error(function(data, status, headers, config) {
	    		 console.log('lol u errored')
		    });
		
	};
	
}]);

ttApp.controller('FileController', ['$scope', '$http', function($scope, $http) {
	$scope.showEditor = false;
	$scope.currentFile = { };
	$scope.setEditor = function(url, checksumId, imgUrl, model){
		$scope.currentFile = model;
		var fullUrl = url + checksumId;
		$http.get(fullUrl).success(function(data, status, headers, config) {
			$scope.showEditor = true;
			$scope.imgUrl = imgUrl;
			$scope.checksumId = data.checksumId;
			$scope.transcriptionId = data.transcriptionId;
			$scope.transcriptionText = data.transcriptionText;
			$scope.transcriptionWordCount = data.transcriptionWordCount;
			$scope.translationId = data.translationId;
			$scope.translationText = data.translationText;
			$scope.translationWordCount = data.translationWordCount;
			console.log('yay');
			console.log(data);
		})
		 .error(function(data, status, headers, config) {
    		 console.log('lol u errored')
	    });
	}
	$scope.submitTranscription = function(url, transcriptionId, text, cid){
		console.log('test: ' +cid);
		var data = {
				id: transcriptionId,
				imageChecksum: {id: cid},
				transcriptionText: text
		};
		$http.post(url, data)
		.success(function(data, status, headers, config) {
			$scope.currentFile.hasTranscription = true;
			$scope.checksumId = data.checksumId;
			$scope.transcriptionId = data.transcriptionId;
			$scope.transcriptionText = data.transcriptionText;
			$scope.transcriptionWordCount = data.transcriptionWordCount;
			console.log($scope.data);
			if(data.imageTranslationList != null && data.imageTranslationList[0].translationText > 0){
				$scope.translationId = data.imageTranslationList[0].id;
				$scope.translationText = data.imageTranslationList[0].translationText;
				$scope.translationNoWords = data.imageTranslationList[0].translationNoWords;
			}
		})
	    .error(function(data, status, headers, config) {
    		 console.log('lol u errored')
	    });
	}
	$scope.submitTranslation = function(url, text, tid){
		var data = {
				imageTranscription: {id: tid},
				translationText: text
		};
		$http.post(url, data)
		.success(function(data, status, headers, config) {
			$scope.currentFile.hasTranslation= true;
			$scope.translationId = data.translationId;
			$scope.translationText = data.translationText;
			$scope.translationWordCount = data.translationWordCount;
		})
	    .error(function(data, status, headers, config) {
    		 console.log('lol u errored')
	    });
	}
}]);

ttApp.controller('utilController', ['$scope', '$http', function($scope, $http) {
	
	$scope.submitImageChecksum = function(url){
		$http.get(url).success(function(){
			console.log('made it');
		});
		}
	$scope.submitFileTranslation = function(url, originUrl){
		var newUrl = url + encodeURIComponent(originUrl);
		$http.get(newUrl).success(function(){
			console.log('made it');
		});
		}
	
}]);

