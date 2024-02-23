package controller;

import model.Student;
import service.classRoom.ClassroomDAO;
import service.classRoom.IClassroomDAO;
import service.student.IStudentDAO;
import service.student.StudentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = {"/students"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IStudentDAO studentDAO;
    private IClassroomDAO classroomDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case"create":
                showNewForm(req,resp);
                break;
            case"delete":
                deleteStudent(req,resp);
                break;
            case"edit":
                showEditForm(req,resp);
            case"search":
                showSearchStudent(req, resp);
                break;
            default:
                showStudentList(req,resp);
        }
    }

    private void showSearchStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "%" + req.getParameter("search") + "%";
        List<Student> studentList = studentDAO.findByName(name);
        req.setAttribute("studentList", studentList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(req,resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentDAO.findByID(id);
        req.setAttribute("student", student);
        req.setAttribute("classroomList",classroomDAO.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/edit.jsp");
        dispatcher.forward(req,resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentDAO.deleteById(id);
        req.setAttribute("classroomList",classroomDAO.getAll());
        showStudentList(req,resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/create.jsp");
        req.setAttribute("classroomList",classroomDAO.getAll());
        dispatcher.forward(req,resp);
    }

    private void showStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentDAO.getAll();
        req.setAttribute("studentList", studentList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        action =action == null ? "" : action;
        switch (action) {
            case "create":
                createNewStudent(req,resp);
                break;
            case "edit":
                updateStudent(req,resp);
                break;
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        int classroomId = Integer.parseInt(req.getParameter("classroom"));
        String classroom = classroomDAO.getNameById(classroomId);
        Student student = new Student(id,name, dob ,email,address,phone,classroom);
        studentDAO.update(student);
//        resp.sendRedirect("/students?action=edit&id="+id);
        resp.sendRedirect("/students?action=students");
    }

    private void createNewStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        int classroomId = Integer.parseInt(req.getParameter("classroom"));
        String classroom = classroomDAO.getNameById(classroomId);
        Student student = new Student(name, dob ,email,address,phone,classroom);
        studentDAO.save(student);
        resp.sendRedirect("/students");
    }

    @Override
    public void init() {
        studentDAO = new StudentDAO();
        classroomDAO = new ClassroomDAO();
    }
}
