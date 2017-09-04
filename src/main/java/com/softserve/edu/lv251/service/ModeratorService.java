package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.dto.pojos.ModeratorDTO;
import com.softserve.edu.lv251.entity.Moderator;

import java.util.List;

/**
 * Created by Admin on 31.07.2017.
 */
public interface ModeratorService {
    Moderator getByEmail(String email);
    List<Moderator> getListByAdminId(Long id);
    Moderator getById(Long id);
    void updateModerator(Moderator moderator);
    void delete(Moderator moderator);
    void add(Moderator moderator);
    void addModeratorAccount(ModeratorDTO addModerator, String adminEmail);
    void editModerator(Long id, Moderator editModerator);
}
