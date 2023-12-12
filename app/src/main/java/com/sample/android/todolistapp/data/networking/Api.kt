package com.sample.android.todolistapp.data.networking

import com.sample.android.todolistapp.data.networking.endpoint.GetTodosEndpoint

// instead of defining endpoints in Api interface, we can define them in separate interfaces
// and then implement them in Api interface, this way we can have a cleaner code
interface Api : GetTodosEndpoint