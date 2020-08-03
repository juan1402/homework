package com.sweatworks.homework.common.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import com.sweatworks.domain.model.User

fun Context.openGoogleMaps(latitude: Double, longitude: Double) {
    val gmmIntentUri: Uri = Uri.parse("geo:$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(packageManager) != null) {
        startActivity(mapIntent)
    }
}

fun Context.addToContacts(user: User) {
    val intent = Intent(ContactsContract.Intents.Insert.ACTION)
    intent.type = ContactsContract.RawContacts.CONTENT_TYPE

    intent.putExtra(
        ContactsContract.Intents.Insert.EMAIL,
        user.email
    )
        .putExtra(
            ContactsContract.Intents.Insert.EMAIL_TYPE,
            ContactsContract.CommonDataKinds.Email.TYPE_HOME
        )
        .putExtra(
            ContactsContract.Intents.Insert.PHONE,
            user.cellPhoneNumber
        )
        .putExtra(
            ContactsContract.Intents.Insert.NAME,
            ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME_PRIMARY
        )
        .putExtra(
            ContactsContract.Intents.Insert.NAME,
            user.firstName + " " + user.lastName
        )

    startActivity(intent)
}