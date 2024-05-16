package com.busanit.customerService.util;

import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

    public static void addPaginationAttributes(Model model, int page, int size, int totalPages) {
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);

        // Calculate page numbers to display
        List<Integer> pageNumbers = calculatePageNumbers(page, totalPages);
        model.addAttribute("pageNumbers", pageNumbers);
    }

    public static List<Integer> calculatePageNumbers(int currentPage, int totalPages) {
        List<Integer> pageNumbers = new ArrayList<>();
        int startPage, endPage;
        int maxPages = 10;

        if (totalPages <= maxPages) {
            startPage = 1;
            endPage = totalPages;
        } else {
            startPage = Math.max(1, currentPage - maxPages / 2);
            endPage = Math.min(totalPages, startPage + maxPages - 1);

            if (endPage - startPage < maxPages - 1) {
                startPage = Math.max(1, endPage - maxPages + 1);
            }
        }

        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }

        return pageNumbers;
    }
}