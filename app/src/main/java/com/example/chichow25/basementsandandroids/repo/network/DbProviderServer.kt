package com.example.chichow25.basementsandandroids.repo.network

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import java.io.FileInputStream
import java.io.FileNotFoundException

/**
 * Created by testaccount on 4/24/18.
 */
class DbProviderServer(port: Int, val context: Context): NanoHTTPD(port){
    override fun serve(uri: String, method: Method, header: Map<String,String>, parameters: Map<String,String>,files: Map<String,String>): Response {
        val answer: String = ""
        var fis: FileInputStream? = null
        try{
            fis = FileInputStream(context.getDir("jsonoutput/dbjson.json", Context.MODE_PRIVATE))
            //TODO: Convert SQL DB (async)
        }
        catch(e: FileNotFoundException){
            e.printStackTrace()
        }
        //return NanoHTTPD.Response(Response.Status.OK, "application/json", fis) weird init stuff here
    }
}