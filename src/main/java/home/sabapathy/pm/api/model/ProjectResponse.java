package home.sabapathy.pm.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProjectResponse {

    private long projectId;
    private String name;
    private int taskCount;
    private Date startDate;
    private Date endDate;
    private int priority;
    private boolean completed;
}
