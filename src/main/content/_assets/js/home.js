function hideAllFeaturedModelsCollapses(element){
    $(".featuredModelsCollapse").collapse('hide');
    $(element).collapse('show');
}

function hideAllOpenSourcePlatformsCollapses(id){
    $(".open_source_platform_content_box").collapse('hide');
    $("#open_source_platform_" + id + "_collapse").collapse('show');
    $(".open_source_platform_box_visible").removeClass("open_source_platform_box_visible");
    $("#open_source_platform_" + id + "_box").addClass("open_source_platform_box_visible");
    $(".open_source_platform_vertical_separator_visible").removeClass("open_source_platform_vertical_separator_visible");
    $("#open_source_platform_" + id + "_separator").addClass("open_source_platform_vertical_separator_visible");
}