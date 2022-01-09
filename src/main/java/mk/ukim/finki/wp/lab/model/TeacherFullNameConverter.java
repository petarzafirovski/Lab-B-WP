package mk.ukim.finki.wp.lab.model;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TeacherFullNameConverter implements AttributeConverter<TeacherFullName,String> {
    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(TeacherFullName teacherFullName) {
        if(teacherFullName ==null){
            return null;
        }

        StringBuilder stringBuilder=new StringBuilder();
        if(teacherFullName.getSurname()!=null && !teacherFullName.getSurname().isEmpty()){
            stringBuilder.append(teacherFullName.getSurname());
            stringBuilder.append(SEPARATOR);
        }

        if(teacherFullName.getName() != null && !teacherFullName.getSurname().isEmpty()){
            stringBuilder.append(teacherFullName.getName());
        }

        return stringBuilder.toString();
    }

    @Override
    public TeacherFullName convertToEntityAttribute(String s) {
       if(s==null || s.isEmpty()){
           return null;
       }

       String [] pieces = s.split(SEPARATOR);

       if(pieces==null || pieces.length == 0){
           return null;
       }

       TeacherFullName teacherFullName = new TeacherFullName();
       String firstPiece = !pieces[0].isEmpty()? pieces[0] : null;
       if(s.contains(SEPARATOR)){
           teacherFullName.setSurname(firstPiece);

           if(pieces.length>=2 && pieces[1] != null && !pieces[1].isEmpty()){
               teacherFullName.setName(pieces[1]);
           }else{
               teacherFullName.setName(firstPiece);
           }
       }
       return teacherFullName;
    }
}
