package application.data.service;


import application.data.model.Banner;


import application.data.repository.BannerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BannerService {
    private static final Logger logger = LogManager.getLogger(BannerService.class);


    @Autowired
    private BannerRepository bannerRepository;

    public void addNewBanner(Banner banner) {
        bannerRepository.save(banner);
    }

    @Transactional
    public void addNewListBanners(List<Banner> listBanners) {
        bannerRepository.save(listBanners);
    }

    public Banner findOne(int bannerId) {
        return bannerRepository.findOne(bannerId);
    }


    public List<Banner> getListAllBanners() {
        try {
            return bannerRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean updateBanner(Banner banner) {
        try {
            bannerRepository.save(banner);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteBanner(int bannerId) {
        try {
            bannerRepository.delete(bannerId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }


}
