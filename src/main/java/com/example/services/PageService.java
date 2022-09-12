package com.example.services;

import com.example.entity.Page;
import com.example.repository.IPageJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.List;

@Service
public class PageService {

    private static final Logger log = LoggerFactory.getLogger(PageService.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IPageJpaRepository repository;

    @Transactional
    public Page add(Long bookId, int number) {
        return add(new Page(new BookService().findById(bookId), number));
    }

    @Transactional
    public Page add(Page page) {
        if (page != null){
            repository.save(page);
            return page;
        }
        return null;
    }

    @Transactional
    public Page delete(Long id) {
        return delete(findById(id));
    }

    @Transactional
    public Page delete(Page page) {
        if (page != null){
            repository.delete(page);
            return page;
        }
        return null;
    }

    public List<Page> findAll() {
        return  repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Page> findAllOfBookId(Long bookId) {
        List<Page> foundPages = repository.findAllOfBookId(bookId);
        return foundPages;
    }

    public Page findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Page> findByNumber(int number) {
        List<Page> foundPages = repository.findByNumber(number);
        foundPages.sort(Comparator.comparing(Page::getNumber));
        return foundPages;
    }

    public List<Page> findByFirstWord(String firstWord) {
        return firstWord != null
                ? repository.findByFirstWord(firstWord)
                : null;
    }

    public Page findByNumberAndBookId(int number, Long bookIid) {
        return repository.findByNumberAndBookId(number, bookIid);
    }

    @Transactional
    public Page update(Long id, String firstWord) {
        Page page = findById(id);
        if (firstWord != null && page != null){
            page.setFirstword(firstWord);
            return update(page);
        }
        return page;
    }

    @Transactional
    public Page update(Page page) {
        repository.save(page);
        return page;
    }
}