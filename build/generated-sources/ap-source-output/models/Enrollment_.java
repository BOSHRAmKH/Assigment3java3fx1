package models;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="٢٠٢٦-٠٥-١٥T١٤:٢٦:٤٤", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Enrollment.class)
public class Enrollment_ { 

    public static volatile SingularAttribute<Enrollment, Integer> studentId;
    public static volatile SingularAttribute<Enrollment, LocalDate> enrollmentDate;
    public static volatile SingularAttribute<Enrollment, Integer> id;
    public static volatile SingularAttribute<Enrollment, Integer> courseId;

}