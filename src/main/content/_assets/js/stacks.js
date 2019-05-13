function filterStacks(){
    //hide a collapse if it's open before we filter and move all of the stack tiles around
    $("#stackCollapse").remove();
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

function toggleStackTileCollapse(element){
    //remove currently opened collapse if it exists
    $("#stackCollapse").remove();
    //figure out what row the element that got clicked on is in
    var firstVisisbleTile = $(".stackTile:visible").first();
    var visibleTiles = $(".stackTile:visible");
    var currentTile = firstVisisbleTile;
    var tilesIndex = 0;
    for(tilesIndex; tilesIndex < visibleTiles.length; tilesIndex++){
        if($(element).is(visibleTiles[tilesIndex])){
            break;
        }
    }
    var rowNumber = Math.floor(tilesIndex / 4);
    //Stack table uses rows of four with 0 indexing.  0th row ends with index 3, 1st ends with index 7, and so on
    var endOfRowIndex = 3 + (4 * rowNumber);
    //If the given row is not full, we have to back the append index up to a tile that exists so the insertAfter function will work
    var appendIndex = visibleTiles.length <= endOfRowIndex ? visibleTiles.length - 1 : endOfRowIndex;
    $(getStackCollapseHtml(element)).insertAfter(visibleTiles[appendIndex]);    
    var positionsFromEndOfRow = endOfRowIndex - tilesIndex;
    appendArrows(positionsFromEndOfRow);
}

function getStackCollapseHtml(element){
    //Once we get designs from design team, this will have to change to return the respective sets of images & text.  For now, just return example html
    return '<div class="col card card-body" id="stackCollapse"><div class="arrowUpInner"></div><div class="arrowUpOuter"></div><div class="row collapseRow"><div class="col-md-2 textAlignCenter"><img src="/img/logos/85x85/helm.png" class="img-fluid"><p>Helm</p></div><div class="col-md-2 textAlignCenter"><img src="/img/logos/85x85/kubernetes.png" class="img-fluid"><p>Kubernetes</p></div><div class="col-md-2 textAlignCenter"><img src="/img/logos/85x85/prometheus.png" class="img-fluid"><p>Prometheus</p></div><div class="col-md-2 textAlignCenter"><img src="/img/logos/85x85/open_liberty.png" class="img-fluid"><p>Open Liberty</p></div><div class="col-md-2 textAlignCenter"><img src="/img/logos/85x85/EclipseChe.png" class="img-fluid"><p>Eclipse Che</p></div><div class="col-md-2 textAlignCenter"><img src="/img/logos/85x85/tekton.png" class="img-fluid"><p>Tekton</p></div></div><div class="row"><div class="col"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.</p></div></div></div>';

}

function appendArrows(tilePosition){
    if(tilePosition === 0){
        $(".arrowUpInner").addClass("rightTileArrow");
        $(".arrowUpOuter").addClass("rightTileArrow");
    }else if(tilePosition === 1){
        $(".arrowUpInner").addClass("rightCenterTileArrow");
        $(".arrowUpOuter").addClass("rightCenterTileArrow");
    }else if(tilePosition === 2){
        $(".arrowUpInner").addClass("leftCenterTileArrow");
        $(".arrowUpOuter").addClass("leftCenterTileArrow");
    }else if(tilePosition === 3){
        $(".arrowUpInner").addClass("leftTileArrow");
        $(".arrowUpOuter").addClass("leftTileArrow");
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