package com.game.books

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import com.game.core.model.Book
import com.group.pow.resources.Resource

class BooksTransformation : Function<LiveData<Resource<ArrayList<Book>>>, LiveData<java.util.ArrayList<Book>> > {

    override fun apply(input: LiveData<Resource<ArrayList<Book>>>?): LiveData<java.util.ArrayList<Book>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}