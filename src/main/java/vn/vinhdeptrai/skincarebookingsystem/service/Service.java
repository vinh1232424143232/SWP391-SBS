package vn.vinhdeptrai.skincarebookingsystem.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import vn.vinhdeptrai.skincarebookingsystem.dto.request.ServiceRequest;
import vn.vinhdeptrai.skincarebookingsystem.dto.response.ServiceResponse;
import vn.vinhdeptrai.skincarebookingsystem.entity.ServiceCategory;
import vn.vinhdeptrai.skincarebookingsystem.exception.AppException;
import vn.vinhdeptrai.skincarebookingsystem.exception.ErrorCode;
import vn.vinhdeptrai.skincarebookingsystem.mapper.ServiceMapper;
import vn.vinhdeptrai.skincarebookingsystem.repository.ServiceCategoryRepository;
import vn.vinhdeptrai.skincarebookingsystem.repository.ServiceRepository;
import vn.vinhdeptrai.skincarebookingsystem.util.CloudinaryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class Service {
    ServiceRepository serviceRepository;
    ServiceMapper serviceMapper;
    CloudinaryUtil cloudinaryUtil;
    ServiceCategoryRepository serviceCategoryRepository;
    public List<ServiceResponse> getAll() {
        List<vn.vinhdeptrai.skincarebookingsystem.entity.Service> services = serviceRepository.findAll();
        if (services.isEmpty()) {
            throw new AppException(ErrorCode.SERVICE_NOT_FOUND);
        }
        return serviceRepository.findAll().stream().map(service -> serviceMapper.toServiceResponse(service)).toList();
    }
    public ServiceResponse getById(int id) {
        vn.vinhdeptrai.skincarebookingsystem.entity.Service service = serviceRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_NOT_FOUND)
        );;
        return serviceMapper.toServiceResponse(service);
    }
    public ServiceResponse create(ServiceRequest serviceRequest, MultipartFile file) throws IOException {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(serviceRequest.getCategoryId()).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_CATE_NOT_FOUND));

        vn.vinhdeptrai.skincarebookingsystem.entity.Service service = serviceMapper.toService(serviceRequest);
        service.setThumbnail(cloudinaryUtil.uploadImage(file));
        service.setCategory(serviceCategory);
        return serviceMapper.toServiceResponse(serviceRepository.save(service));
    }
    public ServiceResponse update(ServiceRequest serviceRequest, int id, MultipartFile file) throws IOException {
        vn.vinhdeptrai.skincarebookingsystem.entity.Service service = serviceRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_NOT_FOUND)
        );
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(serviceRequest.getCategoryId()).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_CATE_NOT_FOUND));
        serviceMapper.updateService(service, serviceRequest);
        if(file != null && !file.isEmpty()) {
            service.setThumbnail(cloudinaryUtil.uploadImage(file));
        }
        service.setCategory(serviceCategory);
        return serviceMapper.toServiceResponse(serviceRepository.save(service));
    }
    public void delete(int id) {
        vn.vinhdeptrai.skincarebookingsystem.entity.Service service = serviceRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_NOT_FOUND)
        );
        serviceRepository.deleteById(id);
    }
}
