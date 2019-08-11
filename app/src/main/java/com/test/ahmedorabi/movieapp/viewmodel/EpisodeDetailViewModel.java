package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.model.TVType;
import com.test.ahmedorabi.movieapp.model.api.Resource;
import com.test.ahmedorabi.movieapp.model.appModels.imagesResponse.ImagesResponse;
import com.test.ahmedorabi.movieapp.model.appModels.seasonResponse.SeasonResponse;
import com.test.ahmedorabi.movieapp.model.repository.EpisodeDetailService;

import javax.inject.Inject;

public class EpisodeDetailViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    static {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<Resource<ImagesResponse>> imagesResponseLiveData;
    private final LiveData<Resource<SeasonResponse>> seasonResponseLiveData;
    private final MutableLiveData<TVType> tvType;


    @Inject
    public EpisodeDetailViewModel(EpisodeDetailService service, @NonNull Application application) {
        super(application);


        this.tvType = new MutableLiveData<>();

        imagesResponseLiveData = Transformations.switchMap(tvType, input -> {

            if (input == null) {
                return ABSENT;
            }

            return service.getImages(input.getId(), input.getsNum(), input.geteNum());

        });


        seasonResponseLiveData = Transformations.switchMap(tvType, input -> {

            if (input == null) {
                return ABSENT;
            }
            return service.getSeason(input.getId(), input.getsNum());

        });


    }


    public void setType(TVType type) {
        this.tvType.setValue(type);
    }


    public LiveData<Resource<ImagesResponse>> getImagesResponseLiveData() {
        return imagesResponseLiveData;
    }


    public LiveData<Resource<SeasonResponse>> getSeasonResponseLiveData() {
        return seasonResponseLiveData;
    }

}