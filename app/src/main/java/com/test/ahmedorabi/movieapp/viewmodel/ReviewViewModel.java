package com.test.ahmedorabi.movieapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.test.ahmedorabi.movieapp.repository.data.MovieType;
import com.test.ahmedorabi.movieapp.api.Resource;
import com.test.ahmedorabi.movieapp.repository.ReviewRepository;
import com.test.ahmedorabi.movieapp.repository.data.reviewModel.ReviewResponse;
import com.test.ahmedorabi.movieapp.util.AbsentLiveData;

import javax.inject.Inject;

public class ReviewViewModel extends AndroidViewModel {

    private final LiveData<Resource<ReviewResponse>> reviewResponseLiveData;
    private final MutableLiveData<MovieType> movieType;


    @Inject
    public ReviewViewModel(ReviewRepository repository, @NonNull Application application) {
        super(application);

        this.movieType = new MutableLiveData<>();

        reviewResponseLiveData = Transformations.switchMap(movieType, input -> {
            if (input == null) {
                return AbsentLiveData.create();
            }else {
                return repository.getReviews(input.getId(),input.getType());

            }
        });


    }


    public LiveData<Resource<ReviewResponse>> getReviewResponseLiveData() {
        return reviewResponseLiveData;
    }


    public void setMovieType(MovieType type) {
        this.movieType.setValue(type);
    }

}
