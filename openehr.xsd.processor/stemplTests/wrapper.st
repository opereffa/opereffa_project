note
	description: "Summary description for {$type_name$}."
	author: ""
	date: "\$Date\$"
	revision: "\$Revision\$"

class
	$type_name$
inherit
	PB_OBJECT_WRAPPER
	redefine
		cpp_serialize_pb_object_to_string, deserialize_pb_object_from_string
	end

	MEMORY
	redefine
		dispose
	end

create
	make
	
feature --init
	make
	do
		$coll_field_init_calls; separator="\n"$
	end
	
feature--collections for repeated members
	$collection_fields; separator="\n"$

feature --collection accessors
	$collection_field_getters; separator="\n"$

	$collection_low_index_getters; separator="\n"$

	$collection_upper_index_getters; separator="\n"$
	
feature --repeating field adders
	$repeating_field_adders; separator="\n"$
	
feature --message type members
	$message_type_members; separator="\n"$

feature --access
	$string_getters; separator="\n\n"$
	
	$string_setters; separator="\n\n"$
	
	$int_getters; separator="\n\n"$
	
	$int_setters; separator="\n\n"$						
	
	$boolean_getters; separator="\n\n"$
	
	$boolean_setters; separator="\n\n"$	
	
	$message_type_field_getters; separator="\n\n"$		

feature --enum wrappers

	$enum_val_fields; separator="\n\n"$		
	
	$enum_getters; separator="\n\n"$		

	$cpp_enum_getters; separator="\n\n"$		

	$enum_setters; separator="\n\n"$		

    $cpp_enum_setters; separator="\n\n"$		

feature --access helpers
	$cpp_string_setters; separator="\n\n"$
	
	$cpp_string_getters; separator="\n\n"$
	
	$cpp_int_getters; separator="\n\n"$
	
	$cpp_int_setters; separator="\n\n"$	
	
	$cpp_boolean_getters; separator="\n\n"$
	
	$cpp_boolean_setters; separator="\n\n"$	
	
	$cpp_repating_field_adders; separator="\n\n"$	
	
	$add_integer_instance_methods; separator="\n\n"$		
	
	$cpp_add_integer_instance_methods; separator="\n\n"$		
	
	$get_integer_field_size_methods; separator="\n\n"$
	
	$cpp_get_integer_field_size_methods; separator="\n\n"$
	
	$get_integer_field_at_methods; separator="\n\n"$		
	
	$cpp_get_integer_field_at_methods; separator="\n\n"$

	$add_boolean_instance_methods; separator="\n\n"$		
	
	$cpp_add_boolean_instance_methods; separator="\n\n"$		
	
	$get_boolean_field_size_methods; separator="\n\n"$
	
	$cpp_get_boolean_field_size_methods; separator="\n\n"$
	
	$get_boolean_field_at_methods; separator="\n\n"$		
	
	$cpp_get_boolean_field_at_methods; separator="\n\n"$			
	
	$add_string_instance_methods; separator="\n\n"$		
	
	$cpp_add_string_instance_methods; separator="\n\n"$		
	
	$get_string_field_size_methods; separator="\n\n"$
	
	$cpp_get_string_field_size_methods; separator="\n\n"$
	
	$get_string_field_at_methods; separator="\n\n"$		
	
	$cpp_get_string_field_at_methods; separator="\n\n"$	
	
	$cpp_message_type_field_getters; separator="\n\n"$
	
feature --pb helpers
	test_pb_obj
	do
		--empty for now
	end
	
	get_pb_object_size:INTEGER
	do
		Result := get_pb_byte_size (get_protoc_ptr)
	end
	
	$create_cpp_object; separator="\n\n"$
	
	serialize_pb_object(p_byte_arr:POINTER)
	do
		cpp_serialize_pb_object(p_byte_arr,get_protoc_ptr, get_pb_object_size)
	end
	
	$serialize_cpp_object; separator="\n\n"$
	
	
	
	$serialize_cpp_object_to_string; separator="\n\n"$
	
	$get_cpp_object_size; separator="\n\n"$
	
	$cpp_release_object; separator="\n\n"$
	
	$cpp_deserialize_pb_object; separator="\n\n"$
	
	$deserialize_pb_object_from_string; separator="\n\n"$
	
	dispose
	do
		if is_root_object then
			release_cpp_object(get_protoc_ptr)
		end
	end

end
