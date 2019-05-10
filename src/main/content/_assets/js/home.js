function showImgText(element){
    $(element).next().show();
}

function hideImgText(element){
    $(element).next().hide();
}

function hideAllFeaturedModelsCollapses(element){
    $(".featuredModelsCollapse").collapse('hide');
    $(element).collapse('show');
}