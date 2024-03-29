import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from './dashboard.component';
import {ChatMessageComponent} from './chat-message/chat-message.component';
import {FriendChatComponent} from './friend-chat/friend-chat.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {ReceivedFriendRequestComponent} from './received-friend-request/received-friend-request.component';
import {SettingsComponent} from './settings/settings.component';
import {AccountComponent} from './settings/account/account.component';
import {ChangePasswordComponent} from './settings/change-password/change-password.component';
import {NewFriendRequestComponent} from './new-friend-request/new-friend-request.component';
import { GroupChatComponent } from './group-chat/group-chat.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

@NgModule({
  declarations: [
    DashboardComponent,
    ChatMessageComponent,
    FriendChatComponent,
    ReceivedFriendRequestComponent,
    NewFriendRequestComponent,
    SettingsComponent,
    AccountComponent,
    ChangePasswordComponent,
    GroupChatComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule
  ],
  exports: [
    ChatMessageComponent
  ]
})
export class DashboardModule {
}
