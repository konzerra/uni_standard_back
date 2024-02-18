package com.konzerra.uni_standard.domain.file.impl

import com.konzerra.uni_standard.domain.file.FileStorageService
import com.konzerra.uni_standard.config.app_config.AppProperties
import com.konzerra.uni_standard.exception.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*


@Service
class FileStorageServiceImpl(
    appProperties: AppProperties
) : FileStorageService {



    private val fileBasePath: String = appProperties.location
    init {
        // Create the base directory if it doesn't exist
        val baseDir = File(fileBasePath)
        if (!baseDir.exists()) {
            baseDir.mkdirs()
        }
    }

    override fun save(multipartFile: MultipartFile, removingFile: String): String {
        this.delete(removingFile)

        val uniqueFilename = getUniqueFileName(multipartFile)
        val targetPath = Paths.get(fileBasePath, uniqueFilename)

        try {
            Files.copy(multipartFile.inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING)
        } catch (e: Exception) {
            throw RuntimeException("Could not save file: ${e.message}", e)
        }

        return uniqueFilename
    }

    override fun delete(name: String) {
        if(name.isEmpty()){
            return
        }
        val targetPath = Paths.get(fileBasePath, name)
        try {
            Files.deleteIfExists(targetPath)
        } catch (e: Exception) {
            throw RuntimeException("Could not delete file: ${e.message}", e)
        }
    }

    override fun findByName(name: String): File {
        val targetPath = Paths.get(fileBasePath, name)
        val file = targetPath.toFile()

        if (!file.exists()) {
            throw BadRequestException("file_not_found")
        }

        return file
    }

    private fun getUniqueFileName(multipartFile: MultipartFile): String {
        val originalFileName = multipartFile.originalFilename
            ?: throw BadRequestException(
                report = "file_name_non_existent"
            )
        println(originalFileName)
        return UUID.randomUUID().toString() + getFileExtension(originalFileName)
    }

    private fun getFileExtension(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf("."))
    }
}