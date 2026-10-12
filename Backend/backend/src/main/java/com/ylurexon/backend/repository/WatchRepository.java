package com.ylurexon.backend.repository;
import com.ylurexon.backend.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public  interface WatchRepository  extends JpaRepository<Watch,Long>,
        JpaSpecificationExecutor<Watch> {

}
