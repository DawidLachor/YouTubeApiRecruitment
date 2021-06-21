package pl.skorpjdk.youtubeapirecruitment;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.*;
import pl.skorpjdk.youtubeapirecruitment.model.*;
import pl.skorpjdk.youtubeapirecruitment.model.PageInfo;
import pl.skorpjdk.youtubeapirecruitment.model.Thumbnail;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class MappingModel {

    public static ListOfSearch searchListResponseToListOfSearch(SearchListResponse response){
        ListOfSearch listOfSearch = new ListOfSearch();
        listOfSearch.setKind(response.getKind());
        listOfSearch.setEtag(response.getEtag());
        listOfSearch.setNextPageToken(response.getNextPageToken());
        listOfSearch.setPrevPageToken(response.getPrevPageToken());
        listOfSearch.setRegionCode(response.getRegionCode());

        PageInfo pageInfo = searchListPageInfoToPageInfo(response.getPageInfo());
        listOfSearch.setPageInfo(pageInfo);

        List<Item> items = new ArrayList<>();
        for (SearchResult searchResult : response.getItems()) {
            Item item = searchListPageInfoToItem(searchResult);
            items.add(item);
        }
        listOfSearch.setItems(items);
        return listOfSearch;
    }

    private static PageInfo searchListPageInfoToPageInfo(com.google.api.services.youtube.model.PageInfo searchPageInfo){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setResultsPerPage(searchPageInfo.getResultsPerPage());
        pageInfo.setTotalRecords(searchPageInfo.getTotalResults());
        return pageInfo;
    }

    private static Item searchListPageInfoToItem(SearchResult searchResult){
        Item item = new Item();
        item.setEtag(searchResult.getEtag());
        item.setKind(searchResult.getKind());

        ResourceId resultId = searchResult.getId();
        item.setKindId(resultId.getKind());
        item.setVideoId(resultId.getVideoId());
        item.setChanelId(resultId.getChannelId());
        item.setPlaylistId(resultId.getPlaylistId());

        Snippet snippet = searchResultSnippetToSnippet(searchResult.getSnippet());
        item.setSnippet(snippet);

        return item;
    }

    private static Snippet searchResultSnippetToSnippet(SearchResultSnippet resultSnippet){
        Snippet snippet = new Snippet();

        snippet.setPublishedAt(getPublishedAt(resultSnippet.getPublishedAt()));

        snippet.setChannelId(resultSnippet.getChannelId());
        snippet.setTitle(resultSnippet.getTitle());
        snippet.setDescription(resultSnippet.getDescription());

        Thumbnails thumbnails = thumbnailDetailsToThumbnails(resultSnippet.getThumbnails());
        snippet.setThumbnails(thumbnails);
        snippet.setChannelTitle(resultSnippet.getChannelTitle());
        snippet.setLiveBroadcastContent(resultSnippet.getLiveBroadcastContent());
        return snippet;
    }

    private static Instant getPublishedAt(DateTime dateTime) {
        return Instant.parse(dateTime.toString());
    }

    private static Thumbnails thumbnailDetailsToThumbnails(ThumbnailDetails thumbnailDetails){
        Thumbnails thumbnails = new Thumbnails();
        Thumbnail defaultThumbnail = ThumbnailFromThumbnailDetailsToThumbnail(thumbnailDetails.getDefault());
        thumbnails.setDefaultThumbnail(defaultThumbnail);
        Thumbnail highThumbnail = ThumbnailFromThumbnailDetailsToThumbnail(thumbnailDetails.getHigh());
        thumbnails.setHigh(highThumbnail);
        Thumbnail mediumThumbnail = ThumbnailFromThumbnailDetailsToThumbnail(thumbnailDetails.getMedium());
        thumbnails.setMedium(mediumThumbnail);
        return thumbnails;
    }

    private static Thumbnail ThumbnailFromThumbnailDetailsToThumbnail(com.google.api.services.youtube.model.Thumbnail yThumbnail){
        Thumbnail thumbnail = new Thumbnail();
        if (yThumbnail != null) {
            thumbnail.setUrl(yThumbnail.getUrl());
            thumbnail.setHeight(yThumbnail.getHeight());
            thumbnail.setWidth(yThumbnail.getWidth());
        }
        return thumbnail;
     }
}
