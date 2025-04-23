import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-module1-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule],
  templateUrl: './module1-form.component.html',
  styleUrls: ['./module1-form.component.css']
})
export class Module1FormComponent {
  module1Form: FormGroup;
  photoFile?: File;
  videoFile?: File;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.module1Form = this.fb.group({
      vesselBoatName: [''],
      imoMmsiRegNo: [''],
      typeOfVesselBoat: [''],
      lpcDateTimeDeparture: [''],
      npcDateTimeDeparture: [''],
      posnOfBoardingTime: [''],
      masterOwnerTandelName: [''],
      masterOwnerTandelContactNo: [''],
      localAgentName: [''],
      localAgentContactNo: [''],
      typeOfFishingNet: [''],
      typeOfFishHeld: [''],
      colourCodingOfBoat: [''],
      fuelAndWaterHeldOnboard: [''],
      violations: [''],
      crewAndNationality: [''],
      communicationAndSafetyEquipment: [''],
      anyOtherInformation: ['']
    });
  }

  onSubmit() {
    if (this.module1Form.valid) {
      const formJson = JSON.stringify(this.module1Form.value);
      const formData = new FormData();

      formData.append('form', formJson);

      if (this.photoFile) {
        formData.append('photoVesselBoat', this.photoFile);
      }

      if (this.videoFile) {
        formData.append('photoVideoVesselBoat', this.videoFile);
      }

      this.http.post('http://localhost:8080/api/module1/submit', formData).subscribe({
        next: (res) => {
          alert('✅ Form submitted successfully!');
          console.log('Response:', res);
          this.module1Form.reset();
          this.photoFile = undefined;
          this.videoFile = undefined;
        },
        error: (err) => {
          alert('❌ Submission failed. Check console.');
          console.error(err);
        }
      });
    } else {
      alert('Please fill all required fields.');
    }
  }

  onFileChange(event: Event, controlName: string) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      if (controlName === 'photoVesselBoat') {
        this.photoFile = file;
      } else if (controlName === 'photoVideoVesselBoat') {
        this.videoFile = file;
      }
    }
  }
}
