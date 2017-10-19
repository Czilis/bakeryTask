package smagabakery.com.bakeryapp.ui


abstract class MVPPresenter<VIEW : MVPView> {
    protected var view: VIEW? = null

    fun attachView(view: VIEW) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

}