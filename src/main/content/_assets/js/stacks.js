$( document ).ready(function() {
    $(".filter").on("change", filterStacks);
});

function filterStacks(){
    let selectedTags = $(".filter:checkbox:checked").map((index,filter) => $(filter).val()).get();

    // if no filter is selected show all tiles
    if(selectedTags.length === 0){
        $(".stackTile").each((index, tile) => {
            $(tile).show();
        });
        return;
    }

    $(".stackTile").each((index, tile) => {
        let currentTileTags = $(tile).data("tags").split(",");

        // filter out the tiles with tags that aren't included in the selected tag filters
        let tagMatches = currentTileTags.filter(tag => selectedTags.includes(tag.trim()));
        tagMatches.length > 0 ? $(tile).show() : $(tile).hide();
    });
}