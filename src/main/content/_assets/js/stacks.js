function filterStacks(){
    var filterCriteria = [];
    filterCriteria.push($(".languageCheck:checked"));
    filterCriteria.push($(".platformCheck:checked"));
    filterCriteria.push($(".toolsCheck:checked"));
    var tiles = $(".stackTile");
    for(var tilesIndex = 0; tilesIndex < tiles.length; tilesIndex++){
        $(tiles[tilesIndex]).show();
        filterTile(tiles[tilesIndex], filterCriteria);
    }
}

function filterTile(tile, filterCriteria){
    var langFilter = filterCriteria[0];
    var platformFilter = filterCriteria[1];
    var toolsFilter = filterCriteria[2];

    if(langFilter.length > 0){
        var tileLanguages = $(tile).data('language');
        var isTileFilteredOnLanguage = true;
        for(var langIndex = 0; langIndex < langFilter.length; langIndex++){
            if(tileLanguages.indexOf($(langFilter[langIndex]).val()) !== -1){
                isTileFilteredOnLanguage = false;
            }
        }
        if(isTileFilteredOnLanguage){
            $(tile).hide();
        }
    }
    if(platformFilter.length > 0){
        var tilePlatforms = $(tile).data('platform');
        var isTileFilteredOnPlatform = true;
        for(var platIndex = 0; platIndex < platformFilter.length; platIndex++){
            if(tilePlatforms.indexOf($(platformFilter[platIndex]).val()) !== -1){
                isTileFilteredOnPlatform = false;
            }
        }
        if(isTileFilteredOnPlatform){
            $(tile).hide();
        }
    }
    if(toolsFilter.length > 0){
        var tileTools = $(tile).data('tools');
        var isTileFilteredOnTools = true;
        for(var toolsIndex = 0; toolsIndex < toolsFilter.length; toolsIndex++){
            if(tileTools.indexOf($(toolsFilter[toolsIndex]).val()) !== -1){
                isTileFilteredOnTools = false;
            }
        }
        if(isTileFilteredOnTools){
            $(tile).hide();
        }
    }
}

function showMicroProfileColor(element){
    element.setAttribute('src', '/img/logos/100x100/microprofle.png');
}

function showMicroProfileBlackWhite(element){
    element.setAttribute('src', '/img/logos/100x100/microprofle_black.png');
}

function showSpringColor(element){
    element.setAttribute('src', '/img/logos/100x100/spring.png');
}

function showSpringBlackWhite(element){
    element.setAttribute('src', '/img/logos/100x100/spring_black.png');
}

function showNodejsColor(element){
    element.setAttribute('src', '/img/logos/100x100/nodejs.png');
}

function showNodejsBlackWhite(element){
    element.setAttribute('src', '/img/logos/100x100/nodejs_black.png');
}

function showSwiftColor(element){
    element.setAttribute('src', '/img/logos/100x100/swift.png');
}

function showSwiftBlackWhite(element){
    element.setAttribute('src', '/img/logos/100x100/swift_black.png');
}

function showServerlessColor(element){
    element.setAttribute('src', '/img/logos/100x100/serverless.png');
}

function showServerlessBlackWhite(element){
    element.setAttribute('src', '/img/logos/100x100/serverless_black.png');
}