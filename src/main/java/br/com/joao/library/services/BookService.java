package br.com.joao.library.services;

import br.com.joao.library.config.S3Config;
import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.domain.book.BookRequestDTO;
import br.com.joao.library.exceptions.EntityNotFoundException;
import br.com.joao.library.repositories.BookRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AmazonS3 s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public BookService(BookRepository bookRepository, AmazonS3 s3Client) {
        this.bookRepository = bookRepository;
        this.s3Client = s3Client;
    }

    public Book createBook(BookRequestDTO data) {
        String imgUrl = null;

        if (data.img() != null)
            imgUrl = uploadImg(data.img());

        Book newBook = new Book(data);
        newBook.setImgUrl(imgUrl);

        return bookRepository.save(newBook);
    }

    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + UUID.randomUUID();
        try {
            File file = convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, fileName, file);
            file.delete();
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    public Book findBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Book not found"));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book editBook(UUID id, BookRequestDTO data) {
        Book book = findBookById(id);
        BeanUtils.copyProperties(data, book);
        if (data.img() != null)
            book.setImgUrl(uploadImg(data.img()));
        return bookRepository.save(book);
    }

}
