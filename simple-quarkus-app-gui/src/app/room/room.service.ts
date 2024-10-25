import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Room } from '../../client/room/model/room';
import { RoomContent } from '../../client/room/model/roomContent';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private httpClient = inject(HttpClient);

  getRooms() {
    return this.httpClient.get<Room[]>('/api/rooms');
  }

  addRoom(roomContent: RoomContent) {
    return this.httpClient.post<Room>('/api/rooms', roomContent);
  }
}
