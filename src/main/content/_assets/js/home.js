function hideAllFeaturedModelsCollapses(element){
    $(".featuredModelsCollapse").collapse('hide');
    $(element).collapse('show');
}

function hideAllOpenSourcePlatformsCollapses(id){
    $(".open_source_platform_content_box").hide();
    $("#open_source_platform_" + id + "_collapse").show();
    $(".open_source_platform_box_selected").removeClass("open_source_platform_box_selected");
    $("#open_source_platform_" + id + "_box").addClass("open_source_platform_box_selected");
    $(".open_source_platform_vertical_separator_visible").removeClass("open_source_platform_vertical_separator_visible");
    $("#open_source_platform_" + id + "_separator").addClass("open_source_platform_vertical_separator_visible");
}