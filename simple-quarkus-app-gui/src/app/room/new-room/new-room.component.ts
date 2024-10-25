import { Component, inject, signal } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-new-room',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './new-room.component.html',
  styleUrl: './new-room.component.css'
})
export class NewRoomComponent {
  private router = inject(Router);
  private roomService = inject(RoomService);

  name = signal('');
  roomNumber = signal('');

  onCancel() {
    this.router.navigate(['/rooms']);
  }

  onSubmit() {
    this.roomService.addRoom({name: this.name(), roomNumber: this.roomNumber()})
      .subscribe(
        (response) => {
          console.log(response);
          this.router.navigate(['/rooms']);
        }
      )
  }
}
