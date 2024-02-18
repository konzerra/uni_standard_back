package com.konzerra.uni_standard.domain.file

import org.springframework.web.multipart.MultipartFile
import java.io.File

interface FileStorageService {

    /**
     * saves file to file system and returns name of the saved file. On save creates unique file name. If removingFile is found, it will be deleted
     *
     */
    fun save(multipartFile: MultipartFile, removingFile: String = "non existent file"):String


    /**
     * deletes file, if file is not found by name, nothing happens
     */
    fun delete(name: String)


    /**
     * returns file if exists or throws BadRequestException if file is not found
     */
    fun findByName(name: String ): File
}