package com.kodak.sampleandroidarchitecturecomponents

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Note(@PrimaryKey var id: Long,
                var title: String,
                var description: String,
                var priority: Int): RealmObject() {
    constructor(): this(0L, "", "", 1)
    constructor(title: String, description: String, priority: Int): this(0L, title, description, priority)
}