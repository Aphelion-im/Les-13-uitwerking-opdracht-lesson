package nl.novi.les13.dto;

import java.util.List;

public class CourseDto {
    public Long id;
    public String title;
    public int sp;

    /* Veld wat de client gaat doorgeven:
    {
    "id": null,
    "title": "Biologie",
    "sp": 5,
    "teacherIds": [
        1,
        2
    ]
    }
     */
    public List<Long> teacherIds; // Waarom geen courseIds in TeacherDto?

    public List<Long> lessonIds;
}
