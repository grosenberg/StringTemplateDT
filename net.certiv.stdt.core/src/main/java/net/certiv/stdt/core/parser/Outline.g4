/*
 * [The "BSD license"]
 * Copyright (c) 2008-2014 Gerald Rosenberg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, STIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

xvisitor grammar Outline;

options {
	parserClass = STGParser;
	superClass  = OutlineAdaptor;
}

@header {
	package net.certiv.stdt.core.parser.gen;

	import net.certiv.stdt.core.parser.OutlineAdaptor;
}

outline
	: group
	| delims
	| imports
	| template
	| dict
	;

group
	: /group
		{ onEntry:	createModule($group, "ST Group Outline");	}
		{ onExit:	blockEnd(); }
	;

delims
	: /group/delimiters
		{ createDelimStatement();	}
	;

imports
	: /group/imports
		{ createImportStatement();	}
	;

template
	: //template
		{ createTemplateStatement($ID.text, $AT != null);	}
	;

dict
	: //dict
		{ createDictStatement($ID.text);	}
	;

