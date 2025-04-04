package vn.vinhdeptrai.skincarebookingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.vinhdeptrai.skincarebookingsystem.enums.AppointmentStatus;
import vn.vinhdeptrai.skincarebookingsystem.enums.PaymentStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentTimeRequest {
    int slotId, therapistId;

}
