package com.konzerra.uni_standard.domain.file

import com.konzerra.uni_standard.ApiPath
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class FileController(private val fileStorageService: FileStorageService) {

    @GetMapping("${ApiPath.publicPath}/file/download/{fileName}")
    fun downloadFile(@PathVariable("fileName") fileName: String): ResponseEntity<ByteArrayResource> {
        val file = fileStorageService.findByName(fileName)
        val resource = ByteArrayResource(file.readBytes())

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${file.name}")
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource)
    }
}