package org.example.employee.controller;

import org.example.employee.dto.EmployeeCreateDTO;
import org.example.employee.dto.EmployeeEditDTO;
import org.example.employee.dto.EmployeeSearchDTO;
import org.example.employee.mapper.EmployeeMapper;
import org.example.employee.model.Department;
import org.example.employee.model.Employee;
import org.example.employee.service.IDepartmentService;
import org.example.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    @Qualifier("employeeService")
    private IEmployeeService employeeService;
    @Autowired
    @Qualifier("departmentService")
    private IDepartmentService departmentService;
    @Autowired
//    @Qualifier("employeeMapper")
    private EmployeeMapper employeeMapper;
    @ModelAttribute("departmentList")
    public List<Department> getDepartmentList() {
        return departmentService.findAll();
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("employeeCreateDTO", new EmployeeCreateDTO());
        return "employee/create";
    }
    @GetMapping("/edit")
    public String showEdit(Model model, Integer id) {
        Employee employee = employeeService.findById(id);
        EmployeeEditDTO employeeEditDTO = employeeMapper.toEmployeeEditDTOFromEmployee(employee);
        model.addAttribute("employeeEditDTO", employeeEditDTO);
        return "employee/edit";
    }
    @GetMapping("")
    public String showList(Model model, EmployeeSearchDTO employeeSearchDTO, @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Employee> employeeList = employeeService.search(employeeSearchDTO, pageable);
        model.addAttribute("employeeSearchDTO", employeeSearchDTO);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("arrayPage", new int[employeeList.getTotalPages()]);
        model.addAttribute("sort", pageable.getSort().toString().replace(": ", ","));
        return "employee/list";
    }
    @GetMapping("/view")
    private String viewEmployee(Model model, Integer id) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee/view";
    }
    @GetMapping("/delete")
    public String deleteEmployee(Integer id, RedirectAttributes redirectAttributes) {
        employeeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xoá thành công");
        return "redirect:/employees";

    }

    @PostMapping("/create")
    public String createEmployee(Model model, @Validated @ModelAttribute("employeeCreateDTO") EmployeeCreateDTO employeeCreateDTO, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        new EmployeeCreateDTO().validate(employeeCreateDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeCreateDTO", employeeCreateDTO);
            return "employee/create";
        }
        Employee employee = employeeMapper.toEmployeeFromEmployeeCreateDTO(employeeCreateDTO);
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/employees";
    }
    @PostMapping("/edit")
    public String updateEmployee(Model model, @Validated @ModelAttribute("employeeEditDTO") EmployeeEditDTO employeeEditDTO, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        new EmployeeEditDTO().validate(employeeEditDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeEditDTO", employeeEditDTO);
            return "employee/edit";
        }
        Employee employee = employeeMapper.toEmployeeFromEmployeeEditDTO(employeeEditDTO);
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công");
        return "redirect:/employees";
    }
}
