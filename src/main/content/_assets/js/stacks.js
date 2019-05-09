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
        for(var langIndex = 0; langIndex < langFilter.length; langIndex++){
            if(tileLanguages.indexOf($(langFilter[langIndex]).val()) === -1){
                $(tile).hide();
            }
        }
    }
    if(platformFilter.length > 0){
        var tilePlatforms = $(tile).data('platform');
        for(var platIndex = 0; platIndex < platformFilter.length; platIndex++){
            if(tilePlatforms.indexOf($(platformFilter[platIndex]).val()) === -1){
                $(tile).hide();
            }
        }
    }
    if(toolsFilter.length > 0){
        var tileTools = $(tile).data('tools');
        for(var toolsIndex = 0; toolsIndex < toolsFilter.length; toolsIndex++){
            if(tileTools.indexOf($(toolsFilter[toolsIndex]).val()) === -1){
                $(tile).hide();
            }
        }
    }
}

